<?php

namespace App\Mail;

use App\Dto\Bill;
use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Mail\Attachment;
use Illuminate\Mail\Mailable;
use Illuminate\Mail\Mailables\Content;
use Illuminate\Mail\Mailables\Envelope;
use Illuminate\Queue\SerializesModels;

class SendBillEmail extends Mailable implements ShouldQueue
{
    use Queueable, SerializesModels;

    public function __construct(private readonly Bill $bill)
    {
    }

    public function envelope(): Envelope
    {
        return new Envelope(
            to: $this->bill->email,
            subject: 'Bill generated',
        );
    }

    public function content(): Content
    {
        return new Content(
            view: 'mail.sendMail',
        );
    }

    public function attachments(): array
    {
        return [Attachment::fromPath($this->bill->pathPdf)];
    }
}
