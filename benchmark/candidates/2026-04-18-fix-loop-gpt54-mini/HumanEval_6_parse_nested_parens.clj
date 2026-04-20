(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (->> (clojure.string/split paren_string #"\s+")
       (remove clojure.string/blank?)
       (mapv (fn [group]
               (second
                (reduce (fn [[depth max-depth] ch]
                          (if (= ch \()
                            [(inc depth) (max max-depth (inc depth))]
                            [(dec depth) max-depth]))
                        [0 0]
                        group))))))