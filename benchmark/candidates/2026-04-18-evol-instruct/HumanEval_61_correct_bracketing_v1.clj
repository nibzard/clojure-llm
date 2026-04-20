(defn balanced-delimiters
  "Given a collection of delimiter characters, return true if it is properly balanced.
  Supports (), [], and {}. A nil input should return false.

  >>> (balanced-delimiters \"()[]{}\")
  true
  >>> (balanced-delimiters \"([{}])\")
  true
  >>> (balanced-delimiters \"([)]\")
  false
  >>> (balanced-delimiters nil)
  false"
  [delims]
  (if (nil? delims)
    false
    (loop [chars (seq delims)
           stack '()]
      (if (empty? chars)
        (empty? stack)
        (let [c (first chars)]
          (cond
            (#{\( \[ \{} c) (recur (rest chars) (conj stack c))
            (and (= c \)) (= (first stack) \()) (recur (rest chars) (rest stack))
            (and (= c \]) (= (first stack) \[)) (recur (rest chars) (rest stack))
            (and (= c \}) (= (first stack) \{)) (recur (rest chars) (rest stack))
            :else false))))))