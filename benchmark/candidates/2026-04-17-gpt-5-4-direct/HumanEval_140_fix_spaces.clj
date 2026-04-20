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
        body (subs s leading)
        prefix (cond
                 (= leading 1) ""
                 (= leading 2) "_"
                 (>= leading 3) "-"
                 :else "")]
    (str prefix
         (apply str
                (mapcat (fn [grp]
                          (let [n (count grp)
                                c (first grp)]
                            (if (= c \space)
                              [(if (> n 1) \- \_)]
                              grp)))
                        (partition-by identity body))))))