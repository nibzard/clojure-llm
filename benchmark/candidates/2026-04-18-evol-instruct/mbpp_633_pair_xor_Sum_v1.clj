(defn pairwise-xor-count
  "Return the number of unordered pairs in `coll` whose bitwise XOR is exactly `target`.

  `coll` may be any sequential collection of integers, including lazy sequences.

  Examples:
  (pairwise-xor-count [1 2 3 1] 2) ;=> 2
  (pairwise-xor-count [5 5 5] 0)   ;=> 3
  (pairwise-xor-count [] 7)        ;=> 0"
  [coll target]
  (let [freqs (frequencies coll)]
    (reduce-kv
      (fn [acc x fx]
        (let [y (bit-xor x target)]
          (cond
            (< x y) (+ acc (* fx (get freqs y 0)))
            (= x y) (+ acc (quot (* fx (dec fx)) 2))
            :else acc)))
      0
      freqs)))