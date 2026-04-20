(defn lps
  "Write a function to find the length of the longest palindromic subsequence in the given string."
  [str]
  (if (empty? str)
    0
    (let [n (count str)
          s-vec (vec str)]
      (loop [i (dec n)
             prev-row (vec (repeat n 0))]
        (if (< i 0)
          (prev-row (dec n))
          (let [curr-row (loop [j i
                                curr (transient prev-row)]
                           (if (< j n)
                             (cond
                               (= i j)
                               (recur (inc j) (assoc! curr j 1))
                               
                               (= (s-vec i) (s-vec j))
                               (recur (inc j) (assoc! curr j (+ 2 (prev-row (dec j)))))
                               
                               :else
                               (recur (inc j) (assoc! curr j (max (prev-row j) (curr (dec j))))))
                             (persistent! curr)))]
            (recur (dec i) curr-row)))))))