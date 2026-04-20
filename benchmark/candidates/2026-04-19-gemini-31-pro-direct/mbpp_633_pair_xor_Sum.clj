(defn pair_xor_Sum
  "Write a clojure function to find the sum of xor of all pairs of numbers in the given list."
  [arr n]
  (reduce +
          (for [i (range 32)
                :let [c (count (filter #(bit-test % i) arr))]]
            (* c (- n c) (bit-shift-left 1 i)))))