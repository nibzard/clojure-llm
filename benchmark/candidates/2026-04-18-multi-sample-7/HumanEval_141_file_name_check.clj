(defn file_name_check
  "Create a function which takes a string representing a file's name, and returns
  'Yes' if the the file's name is valid, and returns 'No' otherwise.
  A file's name is considered to be valid if and only if all the following conditions 
  are met:
  - There should not be more than three digits ('0'-'9') in the file's name.
  - The file's name contains exactly one dot '.'
  - The substring before the dot should not be empty, and it starts with a letter from 
  the latin alphapet ('a'-'z' and 'A'-'Z').
  - The substring after the dot should be one of these: ['txt', 'exe', 'dll']
  Examples:
  >>> (file_name_check "example.txt")
  "Yes"
  >>> (file_name_check "1example.dll")
  "No""
  [file_name]
  (let [s (or file_name "")
        dot-count (count (filter #(= % \.) s))
        digit-count (count (filter #(Character/isDigit ^char %) s))
        parts (clojure.string/split s #"\." -1)
        valid-ext? #{"txt" "exe" "dll"}
        starts-with-letter? (fn [x]
                              (and (not (empty? x))
                                   (Character/isLetter ^char (first x))))]
    (if (and (<= digit-count 3)
             (= dot-count 1)
             (= (count parts) 2)
             (starts-with-letter? (first parts))
             (valid-ext? (second parts)))
      "Yes"
      "No")))