(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [dp (reduce
                 (fn [dp i]
                   (loop [dp' (assoc-in dp [i i] 1)
                          j (inc i)]
                     (if (>= j n)
                       dp'
                       (recur
                        (assoc-in
                         dp'
                         [i j]
                         (if (= (nth s i) (nth s j))
                           (if (= (inc i) j)
                             2
                             (+ 2 (get-in dp' [(inc i) (dec j)] 0)))
                           (max (get-in dp' [(inc i) j] 0)
                                (get-in dp' [i (dec j)] 0))))
                        (inc j)))))
                 (vec (repeat n (vec (repeat n 0))))
                 (range (dec n) -1 -1))]
        (get-in dp [0 (dec n)]))))