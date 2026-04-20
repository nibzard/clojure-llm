(defn check_expression
  "	Write a function to check if the given expression is balanced or not. https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/"
  [exp]
  (let [open->close {\( \) \[ \] \{ \}}
        close->open (zipmap (vals open->close) (keys open->close))]
    (loop [chars (seq (or exp ""))
           stack '()]
      (if-let [ch (first chars)]
        (cond
          (contains? open->close ch)
          (recur (next chars) (conj stack ch))

          (contains? close->open ch)
          (if (= (first stack) (close->open ch))
            (recur (next chars) (rest stack))
            false)

          :else
          (recur (next chars) stack))
        (empty? stack)))))