(defn fix_spaces
  "Given a string text, replace all spaces in it with underscores, 
  and if a string has more than 2 consecutive spaces, 
  then replace all consecutive spaces with - 
  >>> (fix_spaces \" Example\")
  \"Example\"
  >>> (fix_spaces \" Example 1\")
  \"Example_1\"
  >>> (fix_spaces \" Example 2\")
  \"_Example_2\"
  >>> (fix_spaces \" Example 3\")
  \"_Example-3\""
  [text]
  (let [s (or text "")
        leading (count (re-find #"^ *" s))
        body (subs s leading)
        prefix (apply str (repeat (mod leading 3) \_))
        replaced (clojure.string/replace body #" +" #(if (> (count %) 2) "-" "_"))]
    (str prefix replaced)))