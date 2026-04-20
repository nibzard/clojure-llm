(defn normalize-delimiters
  "Given a string text, replace each run of whitespace with a delimiter:
   - a single space becomes \"_\"
   - two or more consecutive spaces become \"-\"

   Leading and trailing whitespace are treated the same as internal whitespace.

   Examples:
   >>> (normalize-delimiters \"Hello World\")
   \"Hello_World\"
   >>> (normalize-delimiters \"Hello  World\")
   \"Hello-World\"
   >>> (normalize-delimiters \"  A B   C  \")
   \"-A_B-C-\"
   >>> (normalize-delimiters nil)
   nil"
  [text]
  (when (some? text)
    (clojure.string/replace text #"\s+"
                            #(if (= 1 (count %)) "_" "-"))))