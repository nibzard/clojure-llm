(defn extract_quotation
  "Write a function to extract values between quotation marks \" \" of the given string."
  [text1]
  (map second (re-seq #"\"([^\"]*)\"" (str text1))))