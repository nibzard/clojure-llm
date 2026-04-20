(defn make_palindrome
  "Find the shortest palindrome that begins with a supplied string."
  [string]
  (if (empty? string)
    ""
    (loop [i 0]
      (let [suffix (subs string i)]
        (if (= suffix (clojure.string/reverse suffix))
          (str string (clojure.string/reverse (subs string 0 i)))
          (recur (inc i)))))))