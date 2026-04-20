(defn lps
  "Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (seq (or str ""))
        n (count s)]
    (cond
      (zero? n) 0
      :else
      (let [dp (vec (repeat n (vec (repeat n 0))))]
        (loop [len 1
               dp dp]
          (if (> len n)
            (get-in dp [0 (dec n)])
            (let [dp (reduce
                      (fn [dp i]
                        (let [j (+ i (dec len))]
                          (assoc-in dp [i j]
                                    (cond
                                      (= len 1) 1
                                      (= (nth s i) (nth s j))
                                      (if (= len 2)
                                        2
                                        (+ 2 (get-in dp [(inc i) (dec j)])))
                                      :else
                                      (max (get-in dp [(inc i) j])
                                           (get-in dp [i (dec j)]))))))
                      dp
                      (range 0 (inc (- n len))))]
              (recur (inc len) dp)))))))