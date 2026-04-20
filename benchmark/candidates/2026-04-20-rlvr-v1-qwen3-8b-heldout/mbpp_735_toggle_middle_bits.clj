(defn toggle_middle_bits
  "	Write a cljthon function to toggle bits of the number except the first and the last bit. https://www.geeksforgeeks.org/toggle-bits-number-expect-first-last-bits/"
  [n]
  (let [mask (fn [x]
               (loop [i 1
                      m 0]
                 (if (= i x)
                   m
                   (recur (+ i 1) (+ m (bit-shift-left 1 (dec i)))))))]
    (if (zero? n)
      0
      (bit-xor n (mask (count (Integer/toBinaryString n)))))))