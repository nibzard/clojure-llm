(defn pair_xor_Sum
  "Write a cljthon function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (let [cnt (long n)
        freq (frequencies arr)
        unique-nums (keys freq)
        total-pairs (quot (* cnt (dec cnt)) 2)
        max-bits (if (seq unique-nums)
                   (inc (Integer/numberOfLeadingZeros (apply max-key #(Long/numberOfLeadingZeros %) unique-nums)))
                   0)]
    (loop [i 0
           sum 0]
      (if (>= i max-bits)
        sum
        (let [mask (bit-shift-left 1 i)
              set-count (reduce (fn [c num] 
                                  (if (zero? (bit-and num mask)) 
                                    c 
                                    (+ c (freq num)))) 
                                0 
                                unique-nums)
              unset-count (- cnt set-count)
              bit-contrib (* (bit-shift-left 1 i) set-count unset-count)]
          (recur (inc i) (+ sum bit-contrib)))))))