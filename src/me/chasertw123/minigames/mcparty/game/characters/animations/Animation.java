package me.chasertw123.minigames.mcparty.game.characters.animations;

import me.chasertw123.minigames.mcparty.Main;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Animation extends BukkitRunnable {

    private ArmorStand entity;
    private List<KeyFrame> keyFrames;
    private int fps, frame, totalFrames;
    private AnimationType animationType;
    private OnAnimationEnd onAnimationEnd;
    private List<Frame> frames;

    public Animation(ArmorStand entity, List<KeyFrame> keyFrames, int fps, AnimationType animationType, OnAnimationEnd onAnimationEnd) {
        if (fps > 20) {
            System.out.println("Someone used over 20FPS. This animation will not be created. Values will be null.");

            return;
        }

        this.entity = entity;
        this.keyFrames = keyFrames;
        this.fps = fps;
        this.animationType = animationType;
        this.frame = 0;
        this.onAnimationEnd = onAnimationEnd;

        this.keyFrames.forEach(kf -> totalFrames += kf.getFramesSinceLastKeyframe());
        this.totalFrames -= this.keyFrames.get(this.keyFrames.size() - 1).getFramesSinceLastKeyframe();

        this.frames = new ArrayList<>();

        System.out.println("Created an animation with " + totalFrames + " frames.");

        // We need to calculate each frame in between each keyframe
        KeyFrame lastFrame = null, nextFrame = null;
        int frameExcess = 0;
        for(int i = 0; i < totalFrames + 1 + this.keyFrames.get(this.keyFrames.size() - 1).getFramesSinceLastKeyframe(); i++) {
            // Check if a keyframe is on this frame
            int frameBuildup = 0;
            boolean foundKeyFrame = false;

            for(KeyFrame kf : keyFrames) {
                frameBuildup += kf.getFramesSinceLastKeyframe();

                if(frameBuildup == i) {
                    foundKeyFrame = true;
                    frameExcess = 1; // It should always be 1x at least!
                    frames.add(kf);
                    lastFrame = kf;
                } else if (foundKeyFrame) {
                    nextFrame = kf;

                    break;
                }
            }

            if(lastFrame != null && nextFrame == null) {
                nextFrame = keyFrames.get(0); // The first frame
                foundKeyFrame = false;
            }

            if(!foundKeyFrame && lastFrame != null && nextFrame != null) {
                int timeGap = nextFrame.getFramesSinceLastKeyframe();

                Map<ArmorStandLocationData.ArmorStandLocationPoint, Object> data = new HashMap<>();

                for(Map.Entry<ArmorStandLocationData.ArmorStandLocationPoint, Object> item
                        : lastFrame.getArmorStandLocationData().getData().entrySet()) {

                    Object nextValue = nextFrame.getArmorStandLocationData().getDataAt(item.getKey());

                    if(nextValue == null) {
                        // This means that the last frame isn't a keyframe, but that isn't possible because
                        // the total frame count is determined by the addition of all of the keyframe's gaps.

                        // This *could* get called if the animator fucked up with calling next frames.

                        continue;
                    }

                    switch(item.getKey().getArmorStandDataType()) {
                        case ANGLE: {
                            // Get the difference between the two Euler Angles.
                            EulerAngle lastAngle = (EulerAngle) item.getValue();
                            EulerAngle nextAngle = (EulerAngle) nextValue;

                            EulerAngle difference = nextAngle.subtract(lastAngle.getX(), lastAngle.getY(), lastAngle.getZ());

                            double frameDifferenceX = difference.getX() / timeGap;
                            double frameDifferenceY = difference.getY() / timeGap;
                            double frameDifferenceZ = difference.getZ() / timeGap;

                            // The Euler angle for this frame is each of those * frame excess. (Plus the start - last frame - location)
                            data.put(item.getKey(), new EulerAngle(lastAngle.getX() + frameDifferenceX * frameExcess,
                                    lastAngle.getY() + frameDifferenceY * frameExcess,
                                    lastAngle.getZ() + frameDifferenceZ * frameExcess));

                            break;
                        }
                        case LOCATION: {
                            //TODO: No point in coding this now as the location system is to be reworked to allow dynamic locations.

                            break;
                        }
                        case BOOLEAN: {} // Carry over
                        case ITEM_STACK: {
                            data.put(item.getKey(), item.getValue());

                            break;
                        }
                    }
                }

                frames.add(new Frame(new ArmorStandLocationData(data)));
                frameExcess++;
            }
        }

    }

    public void start() {
        this.runTaskTimer(Main.getInstance(), 0, 20 / fps);
    }

    public void setOnAnimationEnd(OnAnimationEnd onAnimationEnd) {
        this.onAnimationEnd = onAnimationEnd;
    }

    public void stop() {
        this.cancel();

        onAnimationEnd();
    }

    public ArmorStand getEntity() {
        return entity;
    }

    public List<KeyFrame> getKeyFrames() {
        return keyFrames;
    }

    public int getFps() {
        return fps;
    }

    public int getTotalFrames() {
        return totalFrames;
    }

    public AnimationType getAnimationType() {
        return animationType;
    }

    public Frame getNextFrame() {
        if (frame + 1 >= totalFrames && animationType == AnimationType.LOOP)
            frame = 0;
        else if (frame + 1 >= totalFrames && animationType == AnimationType.SINGLE)
            onAnimationEnd();

        return frames.get(frame++);
    }

    private void onAnimationEnd() {
        if (onAnimationEnd != null)
            onAnimationEnd.onAnimationEnd();
    }

    @Override
    public void run() {
        Frame frame = getNextFrame();

        frame.getArmorStandLocationData().updateAllPoints(entity);
    }

}
