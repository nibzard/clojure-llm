(defn is_nested
  "Create a function that takes a string as input which contains only square brackets.
  The function should return true if and only if there is a valid subsequence of brackets 
  where at least one bracket in the subsequence is nested.
  >>> (is_nested \"[[]]\")
  true
  >>> (is_nested \"[]]]]]]][[[[[]\")
  false
  >>> (is_nested \"[][]\")
  false
  >>> (is_nested \"[]\")
  false
  >>> (is_nested \"[[][]]\")
  true
  >>> (is_nested \"[[]][[\")
  true"
  [string]
  (let [chars (seq string)]
    (loop [remaining chars
           open-count 0
           has-nesting false]
      (if (empty? remaining)
        (and has-nesting (zero? open-count))
        (let [ch (first remaining)]
          (cond
            (= ch \[)
            (recur (rest remaining)
                   (inc open-count)
                   (or has-nesting (>= open-count 1)))
            (= ch \])
            (if (pos? open-count)
              (recur (rest remaining)
                     (dec open-count)
                     has-nesting)
              false)
            :else
            (recur (rest remaining) open-count has-nesting)))))))