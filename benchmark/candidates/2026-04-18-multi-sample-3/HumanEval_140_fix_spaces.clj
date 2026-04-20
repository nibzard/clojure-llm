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
        lead (count (take-while #(= % \space) s))
        rest-s (subs s lead)
        prefix (cond
                 (= lead 1) ""
                 (= lead 2) "_"
                 (> lead 2) "-"
                 :else "")]
    (str prefix
         (clojure.string/replace rest-s #" +" #(if (> (count %) 1) "-" "_")))))