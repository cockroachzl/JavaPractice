
public class TestConstructionOrder {

	public static void main(String[] args) {
		X y = new Y();
	}
}

class X {
    protected int xMask = 0x00ff;
    protected int fullMask;
    public X() {
    	System.out.printf("xMask: %x, fullMask: %x\n", xMask, fullMask);
        fullMask |= xMask;
        System.out.printf("xMask: %x, fullMask: %x\n", xMask, fullMask);
        System.out.printf("yMask: %x\n", mask(0xffff) );
    }
    public int mask(int orig) {
        return (orig & fullMask);
    }
}

class Y extends X {
    protected int yMask = 0xff00;
    public Y() {
    	System.out.printf("xMask: %x, yMask: %x, fullMask: %x\n", xMask, yMask, fullMask);
        fullMask |= yMask;
    	System.out.printf("xMask: %x, yMask: %x, fullMask: %x\n", xMask, yMask, fullMask);
    }
    public int mask(int orig){
    	return yMask;
    }
}