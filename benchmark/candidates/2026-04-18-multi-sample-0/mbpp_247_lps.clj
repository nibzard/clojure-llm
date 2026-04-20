(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [dp (reduce
                 (fn [dp len]
                   (reduce
                     (fn [dp i]
                       (let [j (+ i len -1)]
                         (assoc-in dp [i j]
                                   (if (= (nth s i) (nth s j))
                                     (if (= len 2)
                                       2
                                       (+ 2 (get-in dp [(inc i) (dec j)] 0)))
                                     (max (get-in dp [(inc i) j] 0)
                                          (get-in dp [i (dec j)] 0))))))
                     dp
                     (range 0 (inc (- n len)))))
                 (vec
                   (map-indexed
                     (fn [i _]
                       (assoc (vec (repeat n 0)) i 1))
                     s))
                 (range 2 (inc n)))]
        (get-in dp [0 (dec n)] 0))))