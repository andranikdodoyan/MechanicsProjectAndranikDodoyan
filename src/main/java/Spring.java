public class Spring {

    private double stiffness;

    public Spring() {
        this.stiffness = 1;
    }

    public Spring(double stiffness) {
        this.stiffness = stiffness;
    }

    public double getStiffness() {
        return stiffness;
    }

    public void setStiffness(double stiffness) {
        this.stiffness = stiffness;
    }

    public double[] move(double t, double dt, double x0, double v0) {
        return move(0, t, dt, x0, v0);
    }

    public double[] move(double t, double dt, double x0) {
        return move(t, dt, x0, 0);
    }

    public double[] move(double t0, double t1, double dt, double x0, double v0) {
        return move(t0, t1, dt, x0, v0, 1);

    }

    public double[] move(double t0, double t1, double dt, double x0, double v0, double m)  {
        int size = (int) ((t1-t0) / dt);
        double[] coords = new double[size];
        double omega = Math.sqrt(stiffness/m);
        double x_current = x0;
        double v_current = v0;
        double t_current = t0;
        double a, b;
        if (Math.sin(omega*t_current) == 0) {
            b = x_current / Math.cos(omega*t_current);
            a = v_current / (omega * Math.cos(omega*t_current));
        } else if (Math.cos(omega*t_current) == 0) {
            a = x_current / Math.sin(omega*t_current);
            b = - (v_current / omega * Math.sin(omega*t_current));
        } else {
            a = (x_current / Math.cos(omega*t_current) + v_current / (omega*Math.sin(omega*t_current))) * Math.sin(2*omega*t_current);
            b = (x_current - a * Math.sin(omega*t_current)) / Math.cos(omega*t_current);
        }

        for (int i = 0; i < size; i++) {

            x_current = a * Math.sin(omega*t_current) + b * Math.cos(omega*t_current);
            t_current = t_current + dt;
            coords[i] = x_current;
        }


        return coords;
    }

    public Spring inSeries(Spring that) {
        double k1 = this.getStiffness();
        double k2 = that.getStiffness();
        return new Spring(k1*k2 / (k1+k2));
    }

    public Spring inParallel(Spring that) {
        double k1 = this.getStiffness();
        double k2 = that.getStiffness();
        return new Spring(k1 + k2);
    }
}
