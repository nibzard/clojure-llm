(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [pairs {\) \( \} \{ \] \[}
        open? #{\( \{ \[}
        close? #{\) \} \]}
        balance (fn [ch]
                  (when (contains? pairs ch)
                    (pairs ch)))]
    (loop [chars (seq exp)
           stack []]
      (cond
        (nil? chars) (empty? stack)
        (contains? open? chars) (recur (next chars) (conj stack chars))
        (contains? close? chars) (let [expected (balance chars)]
                                   (if (= expected (peek stack))
                                     (recur (next chars) (pop stack))
                                     false))
        :else (recur (next chars) stack)))))