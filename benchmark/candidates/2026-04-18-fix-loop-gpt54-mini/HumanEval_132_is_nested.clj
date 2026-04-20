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
  (loop [chars (seq string)
         depth 0]
    (cond
      (nil? chars) false
      (= (first chars) \[)
      (if (>= depth 1)
        true
        (recur (next chars) (inc depth)))
      (= (first chars) \])
      (recur (next chars) (max 0 (dec depth)))
      :else
      (recur (next chars) depth))))