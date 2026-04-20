(defn lps
  "Write a function to find the length of the longest palindromic subsequence in the given string."
  [s]
  (if (or (nil? s) (empty? s))
    0
    (let [n (count s)
          dp (vec (replicate n 1))]
      (loop [i (dec n)
             dp dp]
        (if (zero? i)
          (first dp)
          (let [prev-val (get dp i)]
            (loop [j (inc i)
                   dp dp
                   prev-prev prev-val]
              (if (>= j n)
                (recur (dec i) dp)
                (let [current (get dp j)
                      new-val (if (= (nth s i) (nth s j))
                                (+ 2 prev-prev)
                                (max (get dp (dec j)) current))]
                  (recur (inc j) (assoc dp j new-val) current))))))))))