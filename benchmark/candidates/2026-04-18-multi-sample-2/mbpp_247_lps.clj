(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [chars (vec s)]
        (loop [i (dec n)
               dp (vec (repeat n 0))]
          (if (neg? i)
            (dp (dec n))
            (let [new-dp
                  (loop [j i
                         prev 0
                         row dp]
                    (if (= j n)
                      row
                      (if (= j i)
                        (recur (inc j) (dp j) (assoc row j 1))
                        (let [temp (dp j)
                              v (if (= (chars i) (chars j))
                                  (+ prev 2)
                                  (max (row (dec j)) temp))]
                          (recur (inc j) temp (assoc row j v))))))]
              (recur (dec i) new-dp)))))))