package com.alex.post.boy.frame.component;

import com.alex.post.boy.frame.http.GetWithBody;
import com.alex.post.boy.frame.http.HttpMethod;
import com.alex.post.boy.frame.util.ResponseUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;
import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * @author Aliaksandr_Shynkevich
 */
public class RequestPanel extends JPanel {

    private static final String SUBMIT_CAPTION = "Submit";
    private static final String RESPONSE_TITLE = "Response";
    private static final String BODY_TITLE = "Body";
    private static final String REQUEST_TITLE = "Request";
    private JTextField request;
    private JTextArea bodyArea;
    private JTextArea responseArea;
    private JButton submit;
    private JLabel statusLabel;
    private JComboBox<HttpMethod> methodsDropDown;

    public RequestPanel() {
        setLayout(new BorderLayout(4,4 ));

        JPanel rawPanel = new JPanel(new GridLayout(1, 2, 4, 4));
        rawPanel.add(initBodyComponent());
        rawPanel.add(initResponseComponent());

        submit = new JButton(SUBMIT_CAPTION);
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doRequest();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        add(initRequestComponent(), BorderLayout.NORTH);
        add(rawPanel, BorderLayout.CENTER);
        add(submit, BorderLayout.SOUTH);
    }

    private JComponent initBodyComponent() {
        bodyArea = initBodyArea();
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(BODY_TITLE));
        panel.add(ResponseUtil.wrapForScroll(bodyArea), BorderLayout.CENTER);
        return panel;
    }

    private JComponent initResponseComponent() {
        responseArea = initResponseArea();
        statusLabel = new JLabel();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(RESPONSE_TITLE));
        panel.add(ResponseUtil.wrapForScroll(responseArea), BorderLayout.CENTER);
        panel.add(statusLabel, BorderLayout.SOUTH);
        return panel;
    }

    private JComponent initRequestComponent() {
        request = new JTextField();
        methodsDropDown = initMethodsBox();

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder(REQUEST_TITLE));
        panel.add(request, BorderLayout.CENTER);
        panel.add(methodsDropDown, BorderLayout.WEST);
        return panel;
    }

    private JComboBox<HttpMethod> initMethodsBox() {
        JComboBox<HttpMethod> comboBox = new JComboBox<>();
        for (HttpMethod method : HttpMethod.values()) {
            comboBox.addItem(method);
        }
        comboBox.setSelectedItem(HttpMethod.GET);
        return comboBox;
    }

    private JTextArea initBodyArea() {
        JTextArea area = new JTextArea();
        area.setLineWrap(true);
        return area;
    }

    private JTextArea initResponseArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        return area;
    }

    private void doRequest() throws Exception {
        HttpClient client = HttpClientBuilder.create().build();
        GetWithBody httpGet = new GetWithBody((HttpMethod) methodsDropDown.getSelectedItem());

        httpGet.setURI(new URI(request.getText()));
        httpGet.setEntity(new StringEntity(bodyArea.getText()));

        HttpResponse httpResponse = client.execute(httpGet);
        String response = IOUtils.toString(httpResponse.getEntity().getContent());
        statusLabel.setText(ResponseUtil.extractStatus(httpResponse));
        responseArea.setText(ResponseUtil.getPrettyJson(response));
    }
}
