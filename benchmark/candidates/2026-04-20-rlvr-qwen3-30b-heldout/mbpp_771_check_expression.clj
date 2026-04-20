(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [pairs {\) \( \} \{ \] \[}
        valid? (fn [ch] (or (contains? #{\( \[ \{} ch)
                            (contains? pairs ch)))]
    (and (string? exp)
         (every? valid? exp)
         (let [result
               (reduce
                (fn [[balance stack] ch]
                  (cond
                    (nil? balance) [balance stack]
                    (contains? #{\( \[ \{} ch) [(inc balance) (conj stack ch)]
                    (and (seq stack)
                         (= (pairs ch) (peek stack)))
                    [(dec balance) (pop stack)]
                    :else [nil nil]))
                [0 '()]
                exp)]
           (and (some? (first result))
                (zero? (first result)))))))<|endoftext|>