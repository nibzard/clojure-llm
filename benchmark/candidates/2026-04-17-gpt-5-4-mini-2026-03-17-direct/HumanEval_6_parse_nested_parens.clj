(defn parse_nested_parens
  " Input to this function is a string represented multiple groups for nested parentheses separated by spaces.
  For each of the group, output the deepest level of nesting of parentheses.
  E.g. (()()) has maximum two levels of nesting while ((())) has three.
  >>> (parse_nested_parens \"(()()) ((())) () ((())()())\")
  [2 3 1 3]"
  [paren_string]
  (mapv (fn [group]
          (loop [chars (seq group)
                 depth 0
                 max-depth 0]
            (if (empty? chars)
              max-depth
              (let [c (first chars)
                    depth' (if (= c \() (inc depth) (dec depth))
                    max-depth' (max max-depth depth')]
                (recur (rest chars) depth' max-depth')))))
        (clojure.string/split (or paren_string "") #"\s+")))