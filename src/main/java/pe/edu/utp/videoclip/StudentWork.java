package pe.edu.utp.videoclip;


public final class StudentWork {

    private StudentWork() {}

    public static double calculateAudioLevel(
            short[] samples,
            int start,
            int end
    ) {
        if (samples == null || samples.length == 0) return 0.0;

        int s = Math.max(0, start);
        int e = Math.min(samples.length, end);

        if (e <= s) return 0.0;

        long sum = 0;
        for (int i = s; i < e; i++) {
            sum += Math.abs((int) samples[i]);
        }

        double average = sum / (double) (e - s);

        double level = average / 32768.0;

        return Math.max(0.0, Math.min(1.0, level));
    }

    public static int chooseImageIndex(
            double level,
            int frameNumber,
            int totalFrames,
            int imageCount
    ) {
        if (imageCount <= 1) return 0;

        int byLevel = (int) Math.floor(level * imageCount);
        byLevel = Math.max(0, Math.min(imageCount - 1, byLevel));

        int framesPerShift = Math.max(1, totalFrames / (imageCount * 4));
        int timeShift = frameNumber / framesPerShift;

        int index = (byLevel + timeShift) % imageCount;

        return index;
    }

    public static MatrixImage applyEffects(
            MatrixImage base,
            double level,
            int frameNumber,
            int totalFrames
    ) {
        double amplitude = 5.0 + (level * 10.0);
        double angle = Math.sin(frameNumber * 0.12) * amplitude;

        MatrixImage rotated = base.rotate(angle);

        if (level < 0.20) {
            return rotated.blur().brighten(0.75);
        } else if (level < 0.45) {
            return rotated.sharpen();
        } else if (level < 0.70) {
            return rotated.sobel();
        } else {
            return rotated.sharpen().brighten(1.3);
        }
    }
}
