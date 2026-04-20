(defn ascii_value
  "	Write a function to find the ascii value of a character."
  [k]
  (when-let [c (cond
                 (char? k) k
                 (and (string? k) (= 1 (count k))) (first k)
                 :else nil)]
    (int c)))