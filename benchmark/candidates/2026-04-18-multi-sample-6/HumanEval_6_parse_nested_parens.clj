(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (letfn [(max-depth [s]
            (first
             (reduce (fn [[mx depth] ch]
                       (case ch
                         \( (let [d (inc depth)] [(max mx d) d])
                         \) [mx (dec depth)]
                         [mx depth]))
                     [0 0]
                     s)))]
    (if (or (nil? paren_string) (= "" paren_string))
      []
      (->> (clojure.string/split paren_string #"\s+")
           (remove empty?)
           (mapv max-depth)))))