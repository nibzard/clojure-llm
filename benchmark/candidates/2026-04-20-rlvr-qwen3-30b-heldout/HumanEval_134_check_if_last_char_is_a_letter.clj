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
  >>> (check_if_last_char_is_a_letter\"\")
  false"
  [txt]
  (boolean
   (and (string? txt)
        (seq txt)
        (let [c (last txt)]
          (and (Character/isLetter ^char c)
               (or (nil? (second (reverse txt)))
                   (= \space (second (reverse txt)))))))))