(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [pairs {\) \(, \] \[, \} \{}
        opens (set (vals pairs))
        closes (set (keys pairs))]
    (loop [chars (seq (or exp ""))
           stack []]
      (if-let [ch (first chars)]
        (cond
          (opens ch)
          (recur (next chars) (conj stack ch))

          (closes ch)
          (if (= (peek stack) (pairs ch))
            (recur (next chars) (pop stack))
            false)

          :else
          (recur (next chars) stack))
        (empty? stack)))))