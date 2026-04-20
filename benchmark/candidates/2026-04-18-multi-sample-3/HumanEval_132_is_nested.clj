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
  (let [[_ found?]
        (reduce (fn [[depth found?] ch]
                  (cond
                    found? [depth true]
                    (= ch \[) [(inc depth) (>= depth 1)]
                    (= ch \]) [(max 0 (dec depth)) false]
                    :else [depth false]))
                [0 false]
                (or string ""))]
    found?))