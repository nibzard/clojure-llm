(defn lps
  "	Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (let [s (or str "")
        n (count s)]
    (if (zero? n)
      0
      (let [memo (atom {})]
        (letfn [(solve [i j]
                  (if (> i j)
                    0
                    (if (= i j)
                      1
                      (if-let [v (get @memo [i j])]
                        v
                        (let [res (if (= (.charAt s i) (.charAt s j))
                                    (+ 2 (solve (inc i) (dec j)))
                                    (max (solve (inc i) j)
                                         (solve i (dec j))))]
                          (swap! memo assoc [i j] res)
                          res)))))]
          (solve 0 (dec n))))))