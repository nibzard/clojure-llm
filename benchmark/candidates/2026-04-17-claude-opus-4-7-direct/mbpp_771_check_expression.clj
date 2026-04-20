(defn check-expression
  "Check if the given expression has balanced parentheses."
  [exp]
  (let [pairs {\} \{, \] \[, \) \(}
        opening (set (vals pairs))
        closing (set (keys pairs))]
    (loop [s (seq exp)
           stack []]
      (cond
        (nil? s) (empty? stack)
        (opening (first s)) (recur (next s) (conj stack (first s)))
        (closing (first s)) (if (and (seq stack)
                                      (= (first s) (get pairs (peek stack))))
                              (recur (next s) (pop stack))
                              false)
        :else (recur (next s) stack)))))