import java.util.ArrayList;
import java.util.List;

/*
 Problem: Binary Watch

 A binary watch has:
 - 4 LEDs for hours (0–11)
 - 6 LEDs for minutes (0–59)

 Given the number of LEDs that are turned on,
 return all possible times the watch could represent.

 Approach:
 - Try all possible hours (0 to 11)
 - Try all possible minutes (0 to 59)
 - Count total set bits in hour + minute
 - If equal to turnedOn → add formatted time

 Time Complexity: O(1)
 (Because 12 × 60 = 720 → constant)

 Space Complexity: O(1)
*/

class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        
        List<String> result = new ArrayList<>();
        
        for (int h = 0; h < 12; h++) {
            for (int m = 0; m < 60; m++) {
                
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {
                    result.add(String.format("%d:%02d", h, m));
                }
            }
        }
        
        return result;
    }
}
