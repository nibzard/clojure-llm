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
  (let [s (or text "")]
    (->> (re-seq #"\S+| +" s)
         (map-indexed
          (fn [i token]
            (if (= \space (first token))
              (cond
                (and (= i 0) (= 1 (count token))) "")
                (> (count token) 2) "-"
                :else (apply str (repeat (count token) \_)))
              token)))
         (apply str))))