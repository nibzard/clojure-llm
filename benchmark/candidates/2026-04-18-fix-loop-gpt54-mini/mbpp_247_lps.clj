(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (vec str)
        n (count s)]
    (if (zero? n)
      0
      (let [dp (reduce
                (fn [dp len]
                  (reduce
                   (fn [dp i]
                     (let [j (+ i len -1)]
                       (assoc-in dp [i j]
                                 (if (= (s i) (s j))
                                   (if (= len 2)
                                     2
                                     (+ 2 (get-in dp [(inc i) (dec j)] 0)))
                                   (max (get-in dp [(inc i) j] 0)
                                        (get-in dp [i (dec j)] 0))))))
                   dp
                   (range 0 (inc (- n len)))))
                (vec (map (fn [i]
                            (assoc (vec (repeat n 0)) i 1))
                          (range n)))
                (range 2 (inc n)))]
        (get-in dp [0 (dec n)])))))