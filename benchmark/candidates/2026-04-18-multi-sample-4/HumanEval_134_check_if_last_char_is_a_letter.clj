(defn check_if_last_char_is_a_letter
  "Create a function that returns true if the last character
  of a given string is an alphabetical character and is not
  a part of a word, and false otherwise.
  Note: \"word\" is a group of characters separated by space.
  Examples:
  >>> (check_if_last_char_is_a_letter \"apple pie\")
  false
  >>> (check_if_last_char_is_a_letter \"apple pi e\")
  true
  >>> (check_if_last_char_is_a_letter \"apple pi e \")
  false
  >>> (check_if_last_char_is_a_letter \"\")
  false"
  [txt]
  (let [s (or txt "")]
    (and (not (empty? s))
         (let [last-ch (.charAt s (dec (count s)))]
           (and (Character/isLetter last-ch)
                (or (= 1 (count s))
                    (= \space (.charAt s (- (count s) 2)))))))))