(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (let [groups (clojure.string/split paren_string #" ")]
    (mapv (fn [group]
            (loop [chars (seq group)
                   current-depth 0
                   max-depth 0]
              (if chars
                (let [c (first chars)]
                  (cond
                    (= c \() (recur (next chars) (inc current-depth) (max max-depth (inc current-depth)))
                    (= c \)) (recur (next chars) (dec current-depth) max-depth)
                    :else (recur (next chars) current-depth max-depth)))
                max-depth)))
          groups)))