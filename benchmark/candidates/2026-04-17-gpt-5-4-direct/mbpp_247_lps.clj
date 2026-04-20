(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [idxs (range n)
            dp (reduce
                (fn [dp i]
                  (reduce
                   (fn [dp j]
                     (assoc-in dp [i j]
                               (cond
                                 (= i j) 1
                                 (= (nth s i) (nth s j))
                                 (+ 2 (if (> (dec j) (inc i))
                                        (get-in dp [(inc i) (dec j)] 0)
                                        0))
                                 :else
                                 (max (get-in dp [(inc i) j] 0)
                                      (get-in dp [i (dec j)] 0)))))
                   dp
                   (range i n)))
                (vec (repeat n (vec (repeat n 0))))
                (reverse idxs))]
        (get-in dp [0 (dec n)]))))