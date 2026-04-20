(defn is_bored
  "You'll be given a string of words, and your task is to count the number
  of boredoms. A boredom is a sentence that starts with the word \"I\".
  Sentences are delimited by '.', '?' or '!'.
  For example:
  >>> (is_bored \"Hello world\")
  0
  >>> (is_bored \"The sky is blue. The sun is shining. I love this weather\")
  1"
  [S]
  (if (or (nil? S) (= "" S))
    0
    (->> (clojure.string/split S #"[.!?]")
         (map clojure.string/trim)
         (remove empty?)
         (filter #(re-find #"^I\b" %))
         count)))