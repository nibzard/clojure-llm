(defn ascii_value
  "	Write a function to find the ascii value of a character."
  [k]
  (when-let [c (cond
                 (char? k) k
                 (and (string? k) (seq k)) (first k)
                 :else nil)]
    (int c)))