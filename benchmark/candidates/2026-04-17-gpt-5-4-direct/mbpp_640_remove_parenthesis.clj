(defn remove_parenthesis
  "	Write a function to remove the parenthesis and what is inbetween them from a string."
  [items]
  (let [s (or items "")]
    (-> s
        (clojure.string/replace #"\([^()]*\)" "")
        (clojure.string/replace #" +" " ")
        (clojure.string/trim))))