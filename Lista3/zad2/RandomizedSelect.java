import java.util.Random;

class RandomizedSelect extends Select{

    @Override
    protected void doFind() {
        System.err.println("Randomized select rozpoczyna dzialanie");
        setResult(randomizedSelect(0, dataLength()-1, k));
    }
    /**
     * Funkcja dokonujaca losowej partycji
     */
    private int randomizedPartition(int low, int high){
        Random rand = new Random();
        int pivot = rand.nextInt(high-low +1) + low;
        System.err.println("Pivot: "+ pivot); //log
        swap(high, pivot);

        return partition(low, high);
    }
    /**
     * Funkcja zwracajaca pozycje k-tej szukanej statystyki pozycyjnej
     */
    private int randomizedSelect(int low, int high, int i){
        if(low == high)
            return get(low);
        int pivot = randomizedPartition(low, high);
        System.err.println("Pivot: "+ pivot); //log

        int k = pivot - low +1;

        if(i <= k){
            return randomizedSelect(low, pivot, i);
        }else{
            return randomizedSelect(pivot + 1, high, i-k);
        }
    }

}