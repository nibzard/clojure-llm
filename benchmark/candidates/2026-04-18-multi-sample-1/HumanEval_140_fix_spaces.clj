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
        leading (count (take-while #(= % \space) s))
        rest-s (subs s leading)
        prefix (when (= leading 1) "_")]
    (str
      prefix
      (apply str
             (map (fn [grp]
                    (let [n (count grp)
                          c (first grp)]
                      (if (= c \space)
                        (if (>= n 2) "-" "_")
                        grp)))
                  (partition-by #(= % \space) rest-s))))))