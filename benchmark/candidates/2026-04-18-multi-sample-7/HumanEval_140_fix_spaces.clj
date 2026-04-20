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
  (let [groups (re-seq #"\s+" (or text ""))
        leading-spaces (when-let [m (re-find #"^\s+" (or text ""))]
                         (count m))
        body (clojure.string/trim (or text ""))]
    (if (empty? groups)
      body
      (str
       (cond
         (nil? leading-spaces) ""
         (= leading-spaces 1) ""
         (= leading-spaces 2) "_"
         :else "-")
       (-> body
           (clojure.string/replace #"\s{3,}" "-")
           (clojure.string/replace #"\s" "_"))))))