(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (let [groups (if (seq paren_string)
                 (re-seq #"\S+" paren_string)
                 [])]
    (mapv (fn [group]
            (second
             (reduce (fn [[depth max-depth] ch]
                       (case ch
                         \( (let [d (inc depth)] [d (max max-depth d)])
                         \) [(dec depth) max-depth]
                         [depth max-depth]))
                     [0 0]
                     group)))
          groups)))