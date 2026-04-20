(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (letfn [(max-depth [s]
            (first
             (reduce (fn [[max-level level] ch]
                       (case ch
                         \( (let [new-level (inc level)]
                              [(max max-level new-level) new-level])
                         \) [max-level (dec level)]
                         [max-level level]))
                     [0 0]
                     s)))]
    (if (seq paren_string)
      (->> (clojure.string/split paren_string #"\s+")
           (remove empty?)
           (mapv max-depth))
      [])))