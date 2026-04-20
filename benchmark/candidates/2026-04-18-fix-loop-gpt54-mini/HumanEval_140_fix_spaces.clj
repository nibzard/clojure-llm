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
  (let [trimmed (clojure.string/trim text)
        leading (- (count text) (count (clojure.string/triml text)))
        trailing (- (count text) (count (clojure.string/trimr text)))
        edge-underscores (apply str (repeat (+ leading trailing) "_"))
        middle (clojure.string/replace trimmed #" +" #(if (> (count %) 1) "-" "_"))]
    (str edge-underscores middle)))