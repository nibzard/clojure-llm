(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (if (or (nil? paren_string) (= "" paren_string))
    []
    (->> (clojure.string/split paren_string #"\s+")
         (remove empty?)
         (mapv (fn [group]
                 (first
                  (reduce (fn [[max-depth depth] ch]
                            (case ch
                              \( (let [new-depth (inc depth)]
                                   [(max max-depth new-depth) new-depth])
                              \) [max-depth (dec depth)]
                              [max-depth depth]))
                          [0 0]
                          group)))))))