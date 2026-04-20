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
  (let [s (or txt "")
        n (count s)]
    (and (pos? n)
         (let [last-ch (.charAt s (dec n))]
           (and (Character/isLetter last-ch)
                (or (= n 1)
                    (= (.charAt s (- n 2)) \space))))))