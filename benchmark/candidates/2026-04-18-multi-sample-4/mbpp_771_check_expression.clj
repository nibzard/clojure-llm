(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [matching {\) \( \] \[ \} \{}
        opens #{\( \[ \{}
        closes #{\) \] \}}]
    (if (nil? exp)
      true
      (loop [chars (seq exp)
             stack '()]
        (if-let [ch (first chars)]
          (cond
            (opens ch) (recur (next chars) (conj stack ch))
            (closes ch) (if (= (matching ch) (first stack))
                          (recur (next chars) (rest stack))
                          false)
            :else (recur (next chars) stack))
          (empty? stack))))))